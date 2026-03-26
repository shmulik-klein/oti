import UIKit
import OtiFramework

class LetterViewController: UIViewController {

    private let useCase = LetterUseCase()
    private var currentIndex = 0

    private let letterLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.systemFont(ofSize: 280)
        return label
    }()

    private let nameLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.systemFont(ofSize: 60)
        label.textColor = .darkGray
        return label
    }()

    private let counterLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont.systemFont(ofSize: 24)
        label.textColor = .lightGray
        return label
    }()

    private let previousButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("◀", for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 50)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()

    private let nextButton: UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("▶", for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 50)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        setupGestures()
        updateDisplay()
    }

    private func setupUI() {
        view.backgroundColor = UIColor(red: 250/255, green: 248/255, blue: 245/255, alpha: 1.0)

        view.addSubview(letterLabel)
        view.addSubview(nameLabel)
        view.addSubview(counterLabel)
        view.addSubview(previousButton)
        view.addSubview(nextButton)

        previousButton.addTarget(self, action: #selector(previousTapped), for: .touchUpInside)
        nextButton.addTarget(self, action: #selector(nextTapped), for: .touchUpInside)

        NSLayoutConstraint.activate([
            letterLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            letterLabel.centerYAnchor.constraint(equalTo: view.centerYAnchor, constant: -60),

            nameLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            nameLabel.topAnchor.constraint(equalTo: letterLabel.bottomAnchor, constant: 20),

            counterLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            counterLabel.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -30),

            previousButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 50),
            previousButton.centerYAnchor.constraint(equalTo: view.centerYAnchor),

            nextButton.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -50),
            nextButton.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
    }

    private func setupGestures() {
        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        swipeLeft.direction = .left
        view.addGestureRecognizer(swipeLeft)

        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        swipeRight.direction = .right
        view.addGestureRecognizer(swipeRight)
    }

    @objc private func handleSwipe(_ gesture: UISwipeGestureRecognizer) {
        if gesture.direction == .left {
            goToNextLetter()
        } else if gesture.direction == .right {
            goToPreviousLetter()
        }
    }

    @objc private func previousTapped() {
        goToPreviousLetter()
    }

    @objc private func nextTapped() {
        goToNextLetter()
    }

    private func goToNextLetter() {
        currentIndex = useCase.getNextLetter(currentIndex)
        updateDisplay()
    }

    private func goToPreviousLetter() {
        currentIndex = useCase.getPreviousLetter(currentIndex)
        updateDisplay()
    }

    private func updateDisplay() {
        if let letter = useCase.getLetter(currentIndex) {
            letterLabel.text = letter.character
            nameLabel.text = letter.name
            counterLabel.text = "\(currentIndex + 1) / \(useCase.getTotalCount())"
        }
    }
}